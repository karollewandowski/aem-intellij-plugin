// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static co.nums.intellij.aem.htl.psi.HtlTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlAtom;
import co.nums.intellij.aem.htl.psi.HtlStringLiteral;

public class HtlAtomImpl extends ASTWrapperPsiElement implements HtlAtom {

  public HtlAtomImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitAtom(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public HtlStringLiteral getStringLiteral() {
    return findChildByClass(HtlStringLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getFloatNumber() {
    return findChildByType(FLOAT_NUMBER);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getIntegerNumber() {
    return findChildByType(INTEGER_NUMBER);
  }

}
