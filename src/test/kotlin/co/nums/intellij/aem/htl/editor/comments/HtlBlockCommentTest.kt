package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.DOLLAR
import com.intellij.openapi.actionSystem.IdeActions

class HtlBlockCommentTest : HtlCommenterTestBase() {

    override val commentType = IdeActions.ACTION_COMMENT_BLOCK

    fun testSingleLineEmptyBlockCommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
            <caret>    $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
            <!--/**/-->    $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineEmptyBlockCommentInMiddleOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title<caret> @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title<!--/**/--> @ context='text'}
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineEmptyBlockCommentAtEndOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}<caret>
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}<!--/**/-->
            </h2>
            <div>
                <div class="heading">$DOLLAR{properties.heading @ context='html'}</div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockCommentAtBeginningOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
            <selection>        <span>$DOLLAR{'test'}</span></selection>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
            <!--/*        <span>$DOLLAR{'test'}</span>*/-->$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockCommentInMiddleOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span><selection>$DOLLAR{'test'}</selection></span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span><!--/*$DOLLAR{'test'}*/--></span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockCommentAtEndOfLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span><selection>$DOLLAR{properties.heading @ context='html'}</selection>
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span><!--/*$DOLLAR{properties.heading @ context='html'}*/-->
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testSingleLineBlockCommentContainingSingleLineComment() = doCommenterTest("""
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
            <!--/*        &lt;!&ndash;/*<span>$DOLLAR{'test'}</span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}*/-->
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockCommentAtBeginningOfFile() = doCommenterTest("""
            <selection><h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2></selection>
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """, """
            <!--/*<h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>*/-->
            <div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>
            """)

    fun testMultiLineBlockCommentInMiddleOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
                <selection><div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div></selection>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
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

    fun testMultiLineBlockCommentAtEndOfFile() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <selection><div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div></selection>

            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <!--/*<div>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>
            </div>*/-->

            """)

    fun testMultiLineBlockCommentContainingSingleLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><selection>
                <div class="heading">
                    <!--/*<span>$DOLLAR{'test'}</span>*/-->$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div></selection>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><!--/*
                <div class="heading">
                    &lt;!&ndash;/*<span>$DOLLAR{'test'}</span>*/&ndash;&gt;$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            </div>
            """)

    fun testMultiLineBlockCommentContainingMultiLineComment() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><selection>
                <!--/*<div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>*/-->
                <div class="content">$DOLLAR{properties.content @ context='html'}</div></selection>
            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div><!--/*
                &lt;!&ndash;/*<div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>*/&ndash;&gt;
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>*/-->
            </div>
            """)

    fun testMultiLineBlockCommentEndingAtEmptyLine() = doCommenterTest("""
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
            <selection>
                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>

            </selection>

            </div>
            """, """
            <h2>
                $DOLLAR{properties.jcr:title @ context='text'}
            </h2>
            <div>
            <!--/*

                <div class="heading">
                    <span>$DOLLAR{'test'}</span>$DOLLAR{properties.heading @ context='html'}
                </div>
                <div class="content">$DOLLAR{properties.content @ context='html'}</div>

            */-->


            </div>
            """)

}
