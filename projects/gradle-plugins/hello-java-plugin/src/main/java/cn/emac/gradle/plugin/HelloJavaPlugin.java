package cn.emac.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Emac
 * @since 2016-06-23
 */
public class HelloJavaPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().create("helloJava", HelloJavaTask.class);
    }
}
