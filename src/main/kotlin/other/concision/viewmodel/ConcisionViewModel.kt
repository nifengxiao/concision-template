package other.concision.viewmodel

fun concisionViewModel(
        packageName: String,
        functionDir: String,
        pageName: String
) = """
package ${packageName}.ui.${functionDir}

import com.creator.concision.core.viewmodel.BaseViewModel

class ${pageName}ViewModel : BaseViewModel(){

}
"""