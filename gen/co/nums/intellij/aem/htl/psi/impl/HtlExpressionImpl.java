// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlOptionList;
import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlExprNode;
import co.nums.intellij.aem.htl.psi.HtlExpression;

public class HtlExpressionImpl extends ASTWrapperPsiElement implements HtlExpression {

  public HtlExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitExpression(this);
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
  @Nullable
  public HtlOptionList getOptionList() {
    return findChildByClass(HtlOptionList.class);
  }

}
