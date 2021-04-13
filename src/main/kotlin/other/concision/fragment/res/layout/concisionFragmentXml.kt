package other.concision.fragment.res.layout

fun concisionFragmentXml(
        packageName: String,
        pageName: String,
        functionDir: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
         <variable
            name="click"
            type="${packageName}.ui.${functionDir}.${pageName}Fragment.ProxyClick" />
            
         <variable
            name="vm"
            type="${packageName}.ui.${functionDir}.${pageName}ViewModel" />  
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="${packageName}.ui.${functionDir}.${pageName}Fragment">


    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
        """

fun concisionNavigationXml(
        pageName: String,
        fragmentLayoutName: String,
        functionPackageName: String,
        navigationNameId: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<navigation>

     <fragment
        android:id="@+id/${navigationNameId}"
        android:name="${functionPackageName}.${pageName}Fragment"
        android:label="${pageName.toLowerCase()}"
        tools:layout="@layout/$fragmentLayoutName"/> 
        
</navigation>
        """