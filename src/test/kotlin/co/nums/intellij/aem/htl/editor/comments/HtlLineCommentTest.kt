package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.DOLLAR
import com.intellij.openapi.actionSystem.IdeActions

class HtlLineCommentTest : HtlCommenterTestBase() {

    override val commentType = IdeActions.ACTION_COMMENT_LINE

    fun testSingleLineCommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
            <caret>    $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            """, """
            <h2>
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            </h2>
            """)

    fun testSingleLineCommentInMiddleOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title<caret> @ context='text'}
            </h2>
            """, """
            <h2>
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            </h2>
            """)

    fun testSingleLineCommentAtEndOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}<caret>
            </h2>
            """, """
            <h2>
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            </h2>
            """)

    fun testSingleLineCommentContainingSingleLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
            <selection>        <!--/*<span>$DOLLAR{'test'}</span>*/-->$DOLLAR{properties.heading @ context='html'}</selection>
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <!--/*&lt;!&ndash;/*<span>$DOLLAR{'test'}</span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}*/-->
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineCommentAtBeginningOfFile() = doCommenterTest("""
            <selection><h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2></selection>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <!--/*<h2>*/-->
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            <!--/*</h2>*/-->
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineCommentInMiddleOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <selection><div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div></selection>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            </div>
            """)

    fun testMultiLineCommentAtEndOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <selection><div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div></selection>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <!--/*<div>*/-->
                <!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            <!--/*</div>*/-->
            """)

    fun testMultiLineCommentForEntireFile() = doCommenterTest("""
            <selection><h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div></selection>
            """, """
            <!--/*<h2>*/-->
                <!--/*$DOLLAR{properties.jcr:title @ context='text'}*/-->
            <!--/*</h2>*/-->
            <!--/*<div>*/-->
                <!--/*<div class="heading">$DOLLAR{properties.heading @ context='html'}</div>*/-->
                <!--/*<div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            <!--/*</div>*/-->
            """)

}
