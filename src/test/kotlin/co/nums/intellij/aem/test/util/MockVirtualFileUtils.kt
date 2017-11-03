package co.nums.intellij.aem.test.util

import com.intellij.mock.MockVirtualFile

fun directory(name: String) = MockVirtualFile(true, name)

fun file(name: String) = MockVirtualFile(name)

fun MockVirtualFile.withChildren(vararg children: MockVirtualFile): MockVirtualFile {
    for (child in children) {
        this.addChild(child)
    }
    return this
}
