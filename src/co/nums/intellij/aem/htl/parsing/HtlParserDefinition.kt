package co.nums.intellij.aem.htl.parsing

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.psi.HtlFile
import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.Language
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class HtlParserDefinition : ParserDefinition {

    override fun createLexer(project: Project) = HtlLexerAdapter()

    override fun createParser(project: Project) = HtlParser()

    override fun getWhitespaceTokens() = TokenSet.create(TokenType.WHITE_SPACE)

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements() = TokenSet.create(HtlTokenTypes.SINGLE_QUOTED_STRING, HtlTokenTypes.DOUBLE_QUOTED_STRING)

    override fun getFileNodeType() = IFileElementType(Language.findInstance(HtlLanguage::class.java))

    override fun createFile(viewProvider: FileViewProvider) = HtlFile(viewProvider)

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode) = ParserDefinition.SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = HtlTokenTypes.Factory.createElement(node)

}
