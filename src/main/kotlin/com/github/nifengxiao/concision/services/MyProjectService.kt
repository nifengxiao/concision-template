package com.github.nifengxiao.concision.services

import com.github.nifengxiao.concision.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
