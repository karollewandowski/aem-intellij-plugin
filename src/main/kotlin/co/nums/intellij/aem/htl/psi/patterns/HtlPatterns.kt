package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.StandardPatterns.or
import com.intellij.patterns.StandardPatterns.string
import com.intellij.patterns.XmlPatterns.xmlAttributeValue
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType

object HtlPatterns {

    private val propertyObjectsNames = arrayOf("properties", "pageProperties", "inheritedPageProperties")

    val optionIdentifier: ElementPattern<PsiElement> =
            psiElement(HtlTypes.IDENTIFIER)
                    .atStartOf(psiElement(HtlTypes.OPTION))

    val displayContextOptionValue: ElementPattern<PsiElement> =
            psiElement()
                    .inside(psiElement(HtlTypes.STRING_LITERAL).inside(psiElement(HtlTypes.OPTION)))
                    .afterLeafSkipping(
                            or(psiElement(HtlTypes.ASSIGN), psiElement(TokenType.WHITE_SPACE)),
                            psiElement(HtlTypes.IDENTIFIER).withText("context")
                    )

    val globalObjectIdentifier: ElementPattern<PsiElement> =
            psiElement(HtlTypes.IDENTIFIER)
                    .andNot(dotAccessedPropertyIdentifier())
                    .andNot(optionIdentifier)

    private fun dotAccessedPropertyIdentifier() =
            psiElement(HtlTypes.IDENTIFIER)
                    .afterLeaf(psiElement(HtlTypes.DOT))

    val predefinedPropertyIdentifier: ElementPattern<PsiElement> =
            or(
                    psiElement(HtlTypes.IDENTIFIER)
                            .afterLeaf(psiElement(HtlTypes.DOT).afterLeaf(*propertyObjectsNames)),
                    psiElement().inside(psiElement(HtlTypes.STRING_LITERAL))
                            .afterLeaf(psiElement(HtlTypes.LEFT_BRACKET).afterLeaf(*propertyObjectsNames))
            )

    val simpleUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement().inside(xmlAttributeValue().withLocalName(or(
                    string().equalTo("data-sly-use"),
                    string().startsWith("data-sly-use."))))

    val expressionUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement()
                    .inside(psiElement(HtlTypes.STRING_LITERAL)
                            .afterLeafSkipping(psiElement(TokenType.WHITE_SPACE), psiElement(HtlTypes.EXPR_START))
                            .inside(htlBlock("data-sly-use")))

    fun htlBlock(name: String) = HtlPattern().block(name)

}
