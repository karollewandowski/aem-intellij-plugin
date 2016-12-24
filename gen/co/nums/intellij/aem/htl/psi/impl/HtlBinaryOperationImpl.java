// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static co.nums.intellij.aem.htl.psi.HtlTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.*;

public class HtlBinaryOperationImpl extends ASTWrapperPsiElement implements HtlBinaryOperation {

  public HtlBinaryOperationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitBinaryOperation(this);
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
  public List<HtlLogicalOperator> getLogicalOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, HtlLogicalOperator.class);
  }

}
