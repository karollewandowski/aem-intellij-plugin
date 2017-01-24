package co.nums.intellij.aem.htl.file

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

class HtlFileTypeFactory : FileTypeFactory() {

    override fun createFileTypes(fileTypeConsumer: FileTypeConsumer) =
            fileTypeConsumer.consume(HtlFileType, HtlFileType.defaultExtension)

}
