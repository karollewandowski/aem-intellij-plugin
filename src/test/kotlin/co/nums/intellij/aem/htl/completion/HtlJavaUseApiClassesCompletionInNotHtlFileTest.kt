package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.addAemClass

class HtlJavaUseApiClassesCompletionInNotHtlFileTest : HtlCompletionTestBase() {

    fun testShouldNotCompleteUseInNotHtlFile() {
        myFixture.addAemClass("org.apache.sling.scripting.sightly.pojo.Use.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.scripting.sightly.pojo.Use;

            public class TestUse implements Use {
                @Override
                public void init(Bindings bindings) {}
            }
            """)

        checkInHtmlFileDoesNotContainAnyOf(
                """<div data-sly-use.myUse="<caret>"></div>""",
                "TestUse"
        )
    }

}
