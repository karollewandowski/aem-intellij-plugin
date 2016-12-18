// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlOperator;
import co.nums.intellij.aem.htl.psi.HtlVisitor;

public class HtlOperatorImpl extends ASTWrapperPsiElement implements HtlOperator {

  public HtlOperatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitOperator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

}
