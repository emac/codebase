package cn.emac.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloGroovyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('helloGroovy') << {
            println 'Hello, world!'
        }
    }
}