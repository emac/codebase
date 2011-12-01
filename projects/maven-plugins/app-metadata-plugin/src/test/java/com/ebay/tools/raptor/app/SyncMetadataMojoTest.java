/*
 * Created on 2011-11-10 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package com.ebay.tools.raptor.app;

import java.io.File;

import junit.framework.Assert;

import org.apache.maven.Maven;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.junit.Test;

/**
 * @author bishen
 */
public class SyncMetadataMojoTest extends AbstractMojoTestCase
{

    private Maven maven;

    @Override
    protected void setUp()
            throws Exception
    {
        super.setUp();

        this.maven = lookup(Maven.class);
    }

    @Test
    public void testSynchronizeGoalRegister()
            throws Exception
    {
        File baseDir = new File(getBasedir(), "target/test-classes/projects/syncRegister/");
        File pomXml = new File(baseDir, "pom.xml");
        Mojo mojo = lookupMojo("synchronize", pomXml);
        Assert.assertNotNull(mojo);

        MavenProject project = getMavenProject(pomXml);
        setVariableValueToObject(mojo, "project", project);
        mojo.execute();
    }

    @Test
    public void testSynchronizeGoalUpdate()
            throws Exception
    {
        File baseDir = new File(getBasedir(), "target/test-classes/projects/syncUpdate/");
        File pomXml = new File(baseDir, "pom.xml");
        Mojo mojo = lookupMojo("synchronize", pomXml);
        Assert.assertNotNull(mojo);

        MavenProject project = getMavenProject(pomXml);
        setVariableValueToObject(mojo, "project", project);
        mojo.execute();
    }

    private MavenProject getMavenProject(File pom)
    {
        MavenExecutionRequest request = new DefaultMavenExecutionRequest();
        request.setBaseDirectory(pom.getParentFile());
        request.setPom(pom);
        MavenExecutionResult result = this.maven.execute(request);

        return result.getProject();
    }

}
