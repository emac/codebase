/*
 * Created on 2011-11-10 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package com.ebay.tools.raptor.app.model;

import org.apache.maven.project.MavenProject;

/**
 * @author bishen
 */
public class ApplicationMetadata
{

    public final String name;

    public final String version;

    private ApplicationMetadata(String name, String version)
    {
        this.name = name;
        this.version = version;
    }

    /**
     * Constructs an instance from the given maven project.
     * 
     * @param project
     * @return
     */
    public static ApplicationMetadata getInstance(MavenProject project)
    {
        // TODO#EMAC.P! resolve application name and version from META-INF/APPLICATION.MF
        return new ApplicationMetadata(project.getArtifactId(), project.getVersion());
    }

}
