package other.concision.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.concision.activity.res.layout.concisionFragmentXml
import other.concision.activity.src.app_package.concisionFragmentKt
import other.concision.activity.src.app_package.concisionViewModel

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
//        needActivity: Boolean,
//        activityLayoutName: String,
//        needActivityLayout: Boolean,
        needFragment: Boolean,
        fragmentLayoutName: String,
        needFragmentLayout: Boolean,
        functionPackageName: String
) {

    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

//    generateManifest(
//            moduleData = moduleData,
//            activityClass = "${activityClass}Activity",
//            activityTitle = activityClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    // 保存Fragment
    if (needFragment) {
        save(concisionFragmentKt(packageName, functionDir, pageName, fragmentLayoutName,functionPackageName), srcOut.resolve("ui/${functionDir}/${pageName}Fragment.${ktOrJavaExt}"))
    }
    if (needFragmentLayout) {
        save(concisionFragmentXml(packageName, pageName, functionDir), resOut.resolve("layout/${fragmentLayoutName}.xml"))
    }
    save(concisionViewModel(packageName, functionDir,pageName), srcOut.resolve("ui/${functionDir}/${pageName}ViewModel.${ktOrJavaExt}"))

}