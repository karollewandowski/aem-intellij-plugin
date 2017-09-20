package co.nums.intellij.aem.htl.parser

import co.nums.intellij.aem.htl.lexer.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.HtlFile
import co.nums.intellij.aem.htl.psi.HtlFileElementType
import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet

class HtlParserDefinition : ParserDefinition {

    private val whitespaceTokens = TokenSet.create(TokenType.WHITE_SPACE)
    private val stringLiteralElements = TokenSet.create(HtlTypes.SINGLE_QUOTED_STRING, HtlTypes.DOUBLE_QUOTED_STRING)

    override fun createLexer(project: Project) = HtlLexerAdapter()

    override fun createParser(project: Project) = HtlParser()

    override fun getWhitespaceTokens() = whitespaceTokens

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements() = stringLiteralElements

    override fun getFileNodeType() = HtlFileElementType

    override fun createFile(viewProvider: FileViewProvider) = HtlFile(viewProvider)

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode) = ParserDefinition.SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = HtlTypes.Factory.createElement(node)

}
