package other.concision.fragment.src.app_package

fun concisionFragmentKt(
        packageName: String,
        functionDir:String,
        pageName:String,
        fragmentLayoutName:String
)="""
package ${packageName}.ui.${functionDir}

import android.os.Bundle
import ${packageName}.R 
import com.creator.config.app.base.BaseFragment
import ${packageName}.databinding.Fragment${pageName.toLowerCase().capitalize()}Binding

class ${pageName}Fragment: BaseFragment<${pageName}ViewModel, Fragment${pageName.toLowerCase().capitalize()}Binding>(){

    override fun layoutId(): Int {
        return R.layout.${fragmentLayoutName}
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
    }
    
    override fun lazyLoadData() {
        super.lazyLoadData()
    }
    
    inner class ProxyClick {
        
    }
}
"""