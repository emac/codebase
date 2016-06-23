package cn.emac.gradle.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class HelloJavaTask extends DefaultTask {

    @TaskAction
    public void hello() {
        System.out.println("Hello, world!");
    }
}