package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.DOLLAR
import com.intellij.openapi.actionSystem.IdeActions

class HtlLineUncommentTest : HtlCommenterTestBase() {

    override val commentType = IdeActions.ACTION_COMMENT_LINE

    fun testSingleLineUncommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
            <caret>    <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            </h2>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            """)

    fun testSingleLineUncommentInMiddleOfLine() = doCommenterTest("""
            <h2>
                <!--/*$DOLLAR{properties.jcr:title<caret> @ context='text'}*/-->
            </h2>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            """)

    fun testSingleLineUncommentAtEndOfLine() = doCommenterTest("""
            <h2>
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/--><caret>
            </h2>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            """)

    fun testSingleLineUncommentContainingSingleLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <!--/*&lt;!&ndash;/*<span>$DOLLAR{'test'}<caret></span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}*/-->
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <!--/*<span>$DOLLAR{'test'}</span>*/-->$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineUncommentAtBeginningOfFile() = doCommenterTest("""
            <selection><!--/*<h2>*/-->
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            <!--/*</h2>*/--></selection>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineUncommentInMiddleOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <selection><!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/--></selection>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineUncommentAtEndOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <selection><!--/*<div>*/-->
                <!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            <!--/*</div>*/--></selection>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineUncommentForEntireFile() = doCommenterTest("""
            <selection><!--/*<h2>*/-->
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            <!--/*</h2>*/-->
            <!--/*<div>*/-->
                <!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            <!--/*</div>*/--></selection>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

}
