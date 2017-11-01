package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.DOLLAR
import com.intellij.openapi.actionSystem.IdeActions

class HtlBlockUncommentTest : HtlCommenterTestBase() {

    override val commentType = IdeActions.ACTION_COMMENT_BLOCK

    fun testSingleLineEmptyBlockUncommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
            <selection><!--/**/--></selection>    $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
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

    fun testSingleLineEmptyBlockUncommentAtEndOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}<selection><!--/**/--></selection>
            </h2>
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

    fun testSingleLineBlockUncommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
            <selection><!--/*        <span>$DOLLAR{'test'}</span>*/--></selection>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockUncommentInMiddleOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span><selection><!--/*$DOLLAR{'test'}*/--></selection></span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockUncommentAtEndOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span><selection><!--/*$DOLLAR{properties.heading @ context='html'}*/--></selection>
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockUncommentContainingSingleLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
            <selection><!--/*        &lt;!&ndash;/*<span>$DOLLAR{'test'}</span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}*/--></selection>
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

    fun testMultiLineBlockUncommentAtBeginningOfFile() = doCommenterTest("""
            <selection><!--/*<h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>*/--></selection>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockUncommentInMiddleOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <selection><!--/*<div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>*/--></selection>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockUncommentAtEndOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <selection><!--/*<div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>*/--></selection>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockUncommentContainingSingleLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><selection><!--/*
                <div class="heading">
                    &lt;!&ndash;/*<span>$DOLLAR{'test'}</span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>*/--></selection>
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

    fun testMultiLineBlockUncommentContainingMultiLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><selection><!--/*
                &lt;!&ndash;/*<div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>*/&ndash;&gt;
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>*/--></selection>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <!--/*<div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>*/-->
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockUncommentEndingAtEmptyLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
            <selection><!--/*

                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>

            */--></selection>


            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>

                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>



            </div>
            """)

}
