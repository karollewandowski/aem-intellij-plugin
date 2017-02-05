package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.psi.HtlElementTypes
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
            psiElement(HtlElementTypes.IDENTIFIER)
                    .atStartOf(psiElement(HtlElementTypes.OPTION))

    val displayContextOptionValue: ElementPattern<PsiElement> =
            psiElement()
                    .inside(psiElement(HtlElementTypes.OPTION))
                    .afterLeafSkipping(
                            or(psiElement(HtlElementTypes.ASSIGN), psiElement(TokenType.WHITE_SPACE)),
                            psiElement(HtlElementTypes.IDENTIFIER).withText("context")
                    )

    val globalObjectIdentifier: ElementPattern<PsiElement> =
            psiElement(HtlElementTypes.IDENTIFIER)
                    .andNot(dotAccessedPropertyIdentifier())
                    .andNot(optionIdentifier)

    private fun dotAccessedPropertyIdentifier() =
            psiElement(HtlElementTypes.IDENTIFIER)
                    .afterLeaf(psiElement(HtlElementTypes.DOT))

    val predefinedPropertyIdentifier: ElementPattern<PsiElement> =
            or(
                    psiElement(HtlElementTypes.IDENTIFIER)
                            .afterLeaf(psiElement(HtlElementTypes.DOT).afterLeaf(*propertyObjectsNames)),
                    psiElement().inside(psiElement(HtlElementTypes.STRING_LITERAL))
                            .afterLeaf(psiElement(HtlElementTypes.LEFT_BRACKET).afterLeaf(*propertyObjectsNames))
            )

    val simpleUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement().inside(xmlAttributeValue().withLocalName(string().startsWith("data-sly-use")))

    val expressionUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement()
                    .inside(psiElement(HtlElementTypes.STRING_LITERAL)
                            .afterLeafSkipping(psiElement(TokenType.WHITE_SPACE), psiElement(HtlElementTypes.EXPR_START))
                            .inside(htlBlock("data-sly-use")))

    fun htlBlock(name: String) = HtlPattern().block(name)

}
