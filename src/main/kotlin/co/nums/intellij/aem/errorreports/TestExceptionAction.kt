package co.nums.intellij.aem.errorreports

import com.intellij.openapi.actionSystem.*

class TestExceptionAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) = throw RuntimeException("Expected exception for test")

}
