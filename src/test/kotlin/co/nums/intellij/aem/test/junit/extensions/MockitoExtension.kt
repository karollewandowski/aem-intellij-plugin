package co.nums.intellij.aem.test.junit.extensions

import org.junit.jupiter.api.extension.*
import org.junit.jupiter.api.extension.ExtensionContext.Namespace.create
import org.mockito.*
import org.mockito.Mockito.mock
import java.lang.reflect.Parameter

class MockitoExtension : TestInstancePostProcessor, ParameterResolver {

    override fun postProcessTestInstance(testInstance: Any, context: ExtensionContext) =
            MockitoAnnotations.initMocks(testInstance)

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
            parameterContext.parameter.isAnnotationPresent(Mock::class.java)


    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
            getMock(parameterContext.parameter, extensionContext)

    private fun getMock(parameter: Parameter, extensionContext: ExtensionContext): Any {
        val mockType = parameter.type
        val mocks = extensionContext.getStore(create(MockitoExtension::class.java, mockType))
        val mockName = getMockName(parameter)
        return if (mockName != null) {
            mocks.getOrComputeIfAbsent(mockName) { mock(mockType, mockName) }
        } else {
            mocks.getOrComputeIfAbsent(mockType.canonicalName) { mock(mockType) }
        }
    }

    private fun getMockName(parameter: Parameter): String? {
        val explicitMockName = parameter.getAnnotation(Mock::class.java).name.trim()
        if (!explicitMockName.isEmpty()) {
            return explicitMockName
        } else if (parameter.isNamePresent) {
            return parameter.name
        }
        return null
    }

}
