package other.concision.activity.src.app_package

fun concisionActivityKt(
        packageName: String,
        functionDir:String,
        pageName:String,
        activityLayoutName:String,
)="""
package ${packageName}.ui.${functionDir}

import android.os.Bundle
import ${packageName}.R 
import com.creator.config.app.base.BaseActivity
import ${packageName}.databinding.Activity${pageName.toLowerCase().capitalize()}Binding

class ${pageName}Activity: BaseActivity<${pageName}ViewModel, Activity${pageName.toLowerCase().capitalize()}Binding>(){

    override fun layoutId(): Int {
        return R.layout.${activityLayoutName}
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
    }
    
    override fun createObserver() {

    }
    
    inner class ProxyClick {
        
    }
}
"""