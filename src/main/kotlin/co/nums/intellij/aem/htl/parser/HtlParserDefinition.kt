package co.nums.intellij.aem.htl.parser

import co.nums.intellij.aem.htl.lexer.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.*
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.tree.TokenSet

class HtlParserDefinition : ParserDefinition {

    private val whitespaceTokens = TokenSet.create(TokenType.WHITE_SPACE)
    private val commentTokens = TokenSet.create(HtlTypes.TEMPLATE_COMMENT, HtlTypes.COMMENT_START, HtlTypes.COMMENT_CONTENT, HtlTypes.COMMENT_END)
    private val stringLiteralElements = TokenSet.create(HtlTypes.STRING_LITERAL)

    override fun createLexer(project: Project) = HtlLexerAdapter()

    override fun createParser(project: Project) = HtlParser()

    override fun getWhitespaceTokens() = whitespaceTokens

    override fun getCommentTokens() = commentTokens

    override fun getStringLiteralElements() = stringLiteralElements

    override fun getFileNodeType() = HtlFileElementType

    override fun createFile(viewProvider: FileViewProvider) = HtlFile(viewProvider)

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode) = ParserDefinition.SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = HtlTypes.Factory.createElement(node)

}
