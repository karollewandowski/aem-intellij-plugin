// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static co.nums.intellij.aem.htl.psi.HtlTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlExprNode;
import co.nums.intellij.aem.htl.psi.HtlOption;

public class HtlOptionImpl extends ASTWrapperPsiElement implements HtlOption {

  public HtlOptionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitOption(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public HtlExprNode getExprNode() {
    return findChildByClass(HtlExprNode.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
