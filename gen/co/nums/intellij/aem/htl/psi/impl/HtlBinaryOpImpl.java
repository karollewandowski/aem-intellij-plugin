// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import java.util.List;

import co.nums.intellij.aem.htl.psi.HtlBinaryOp;
import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlComparisonTerm;
import co.nums.intellij.aem.htl.psi.HtlOperator;

public class HtlBinaryOpImpl extends ASTWrapperPsiElement implements HtlBinaryOp {

  public HtlBinaryOpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitBinaryOp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<HtlComparisonTerm> getComparisonTermList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, HtlComparisonTerm.class);
  }

  @Override
  @NotNull
  public List<HtlOperator> getOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, HtlOperator.class);
  }

}
