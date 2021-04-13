package other.concision.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.concision.activity.androidManifest.manifestTemplateXml
import other.concision.fragment.res.layout.concisionFragmentXml
import other.concision.activity.res.layout.concisionActivityXml
import other.concision.activity.src.app_package.concisionActivityKt
import other.concision.fragment.res.layout.concisionNavigationXml
import other.concision.fragment.src.app_package.concisionFragmentKt
import other.concision.viewmodel.concisionViewModel

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:    菜单
 */
fun RecipeExecutor.concisionRecipe(
        moduleData: ModuleTemplateData,
        packageName: String,
        functionDir: String,
        pageName: String,
        needActivity: Boolean,
        activityLayoutName: String,
        needActivityLayout: Boolean,
        needFragment: Boolean,
        fragmentLayoutName: String,
        needFragmentLayout: Boolean,
        functionPackageName: String,
        needToNavigation: Boolean,
        navigationNameId:String
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    if (needActivity && needFragment) {
        throw IllegalArgumentException("activity和fragment不可同时选择")
    }

    //保存activity
    if (needActivity) {
        mergeXml(manifestTemplateXml(packageName, functionPackageName, "${pageName}Activity"), manifestOut.resolve("AndroidManifest.xml"))
        save(concisionActivityKt(packageName, functionDir, pageName, activityLayoutName), srcOut.resolve("ui/${functionDir}/${pageName}Activity.${ktOrJavaExt}"))
    }

    //保存activity_layout
    if (needActivity && needActivityLayout) {
        save(concisionActivityXml(packageName, pageName, functionDir), resOut.resolve("layout/${activityLayoutName}.xml"))
    }

    // 保存Fragment
    if (needFragment) {
        save(concisionFragmentKt(packageName, functionDir, pageName, fragmentLayoutName), srcOut.resolve("ui/${functionDir}/${pageName}Fragment.${ktOrJavaExt}"))
    }

    //保存fragment_layout
    if (needFragment && needFragmentLayout) {
        save(concisionFragmentXml(packageName, pageName, functionDir), resOut.resolve("layout/${fragmentLayoutName}.xml"))
    }

    //是否添加到navigation中
    if (needToNavigation) {
        mergeXml(concisionNavigationXml(pageName, fragmentLayoutName,functionPackageName,navigationNameId), resOut.resolve("navigation/navigation.xml"))
    }

    //保存ViewModel
    save(concisionViewModel(packageName, functionDir, pageName), srcOut.resolve("ui/${functionDir}/${pageName}ViewModel.${ktOrJavaExt}"))

}