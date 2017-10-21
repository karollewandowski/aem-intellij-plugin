package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.StandardPatterns.or
import com.intellij.patterns.StandardPatterns.string
import com.intellij.patterns.XmlPatterns.xmlAttributeValue
import com.intellij.psi.*

object HtlPatterns {

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

    val variable: ElementPattern<PsiElement> = psiElement(HtlTypes.IDENTIFIER).inside(psiElement(HtlTypes.VARIABLE))

    private val propertyIdentifierInDotPropertyAccess =
            psiElement(HtlTypes.IDENTIFIER)
                    .afterLeaf(psiElement(HtlTypes.DOT))
                    .inside(psiElement(HtlTypes.DOT_PROPERTY_ACCESS))

    private val propertyIdentifierInBracketPropertyAccess =
            psiElement()
                    .inside(psiElement(HtlTypes.STRING_LITERAL)
                            .inside(psiElement(HtlTypes.BRACKET_PROPERTY_ACCESS)))

    val propertyIdentifier: ElementPattern<PsiElement> =
            or(propertyIdentifierInDotPropertyAccess, propertyIdentifierInBracketPropertyAccess)

    val simpleUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement().inside(xmlAttributeValue().withLocalName(or(
                    string().equalTo(HtlBlock.USE.type),
                    string().startsWith("${HtlBlock.USE.type}."))))

    val expressionUseObjectDeclaration: ElementPattern<PsiElement> =
            psiElement()
                    .inside(psiElement(HtlTypes.STRING_LITERAL)
                            .afterLeafSkipping(psiElement(TokenType.WHITE_SPACE), psiElement(HtlTypes.EXPR_START))
                            .inside(htlBlock(HtlBlock.USE.type)))

    fun htlBlock(name: String) = HtlPattern().block(name)

}
