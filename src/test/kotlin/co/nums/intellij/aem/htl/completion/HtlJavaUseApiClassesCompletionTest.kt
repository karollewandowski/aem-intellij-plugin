package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.*

class HtlJavaUseApiClassesCompletionTest : HtlCompletionTestBase() {

    fun testShouldCompleteSlingUseImplementer() {
        myFixture.addAemClass("org.apache.sling.scripting.sightly.pojo.Use.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.scripting.sightly.pojo.Use;

            public class TestUse implements Use {
                @Override
                public void init(Bindings bindings) {}
            }
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="<caret>"></div>""",
                "TestUse"
        )
    }

    fun testShouldCompleteSightlyUseImplementer() {
        myFixture.addAemClass("io.sightly.java.api.Use.java")
        myFixture.addClass("""
            package test;

            import io.sightly.java.api.Use;

            public class TestUse implements Use {
                @Override
                public void init(Bindings bindings) {}
            }
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="<caret>"></div>""",
                "TestUse"
        )
    }

    fun testShouldCompleteSlingModels() {
        myFixture.addAemClass("org.apache.sling.models.annotations.Model.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.models.annotations.Model;

            @Model
            public class TestModel  {}
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="<caret>"></div>""",
                "TestModel"
        )
    }

    fun testShouldCompleteSlingUseImplementerInHtlExpression() {
        myFixture.addAemClass("org.apache.sling.scripting.sightly.pojo.Use.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.scripting.sightly.pojo.Use;

            public class TestUse implements Use {
                @Override
                public void init(Bindings bindings) {}
            }
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="$DOLLAR{'<caret>'}"></div>""",
                "TestUse"
        )
    }

    fun testShouldCompleteSightlyUseImplementerInHtlExpression() {
        myFixture.addAemClass("io.sightly.java.api.Use.java")
        myFixture.addClass("""
            package test;

            import io.sightly.java.api.Use;

            public class TestUse implements Use {
                @Override
                public void init(Bindings bindings) {}
            }
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="$DOLLAR{'<caret>'}"></div>""",
                "TestUse"
        )
    }

    fun testShouldCompleteSlingModelsInHtlExpression() {
        myFixture.addAemClass("org.apache.sling.models.annotations.Model.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.models.annotations.Model;

            @Model
            public class TestModel  {}
            """)

        checkContainsAll(
                """<div data-sly-use.myUse="$DOLLAR{'<caret>'}"></div>""",
                "TestModel"
        )
    }

    fun testShouldNotCompleteInBlockOtherThanUse() {
        myFixture.addAemClass("org.apache.sling.models.annotations.Model.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.models.annotations.Model;

            @Model
            public class TestModel  {}
            """)

        checkDoesNotContainAnyOf(
                """<div data-sly-test="<caret>"></div>""",
                "TestModel"
        )
    }

    fun testShouldNotCompleteInExpressionOfBlockOtherThanUse() {
        myFixture.addAemClass("org.apache.sling.models.annotations.Model.java")
        myFixture.addClass("""
            package test;

            import org.apache.sling.models.annotations.Model;

            @Model
            public class TestModel  {}
            """)

        checkDoesNotContainAnyOf(
                """<div data-sly-test="$DOLLAR{'<caret>'}"></div>""",
                "TestModel"
        )
    }

}
