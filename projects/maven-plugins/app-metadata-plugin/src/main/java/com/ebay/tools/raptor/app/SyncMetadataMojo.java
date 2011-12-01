/*
 * Created on 2011-11-10 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package com.ebay.tools.raptor.app;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import com.ebay.tools.raptor.app.model.ApplicationMetadata;

/**
 * Mojo to synchronize (register/update) application metadata.
 * 
 * @author bishen
 * @goal synchronize
 * @phase deploy
 */
public class SyncMetadataMojo extends AbstractMojo
{

    private static final String DEFAULT_LOCATION = "WebContent/META-INF/raptor-app.xml";

    /**
     * Location of application metadata file (relative to project root folder).
     * 
     * @parameter expression="${metadata.location}" default-value="WebContent/META-INF/raptor-app.xml"
     */
    private String              location;

    /**
     * User id of on-call manager. Must be provided in case you intend to register new application metadata.
     * 
     * @parameter expression="${metadata.onCallManager}"
     */
    private String              onCallManager;

    /**
     * Allow to overwrite existing metadata. Must be set to "true" if you intend to update existing application
     * metadata.
     * 
     * @parameter expression="${metadata.overwrite}" default-value="false"
     */
    private boolean             overwrite;

    /**
     * @parameter default-value="${project}"
     */
    private MavenProject        project;

    private ApplicationMetadata metadata;

    public void execute()
            throws MojoExecutionException, MojoFailureException
    {
        checkProperties();
        initialize();

        // TODO#EMAC.P! call API to get latest status
        boolean registered = this.overwrite;

        // register/update metadata
        if ( !registered )
        {
            if ( StringUtils.isEmpty(this.onCallManager) )
            {
                throw new MojoExecutionException(
                        "Property 'metadata.onCallManager' must not be empty in order to register new application metadata.");
            }

            doRegister();
        }
        else
        {
            if ( !this.overwrite )
            {
                throw new MojoExecutionException(
                        "Property 'metadata.overwrite' must be set to 'true' in order to update an existing application metadata.");
            }

            doUpdate();
        }
    }

    private void checkProperties()
            throws MojoExecutionException
    {
        // check location
        if ( StringUtils.isEmpty(this.location) )
        {
            this.location = DEFAULT_LOCATION;
        }

        File metadata = new File(this.project.getBasedir(), this.location);
        if ( !metadata.isFile() )
        {
            throw new MojoExecutionException("Property 'metadata.location' is referring to a non-existing file.");
        }

        // TODO#EMAC.P? check whether the given file is a valid application metadata file
    }

    private void initialize()
    {
        this.metadata = ApplicationMetadata.getInstance(this.project);
    }

    private void doRegister()
    {
        // TODO#EMAC.P! call API to register metadata
        getLog().info("New application metadata registered: " + this.metadata.name + "-" + this.metadata.version);
    }

    private void doUpdate()
    {
        // TODO#EMAC.P! call API to update metadata
        getLog().info("Application metadata updated: " + this.metadata.name + "-" + this.metadata.version);
    }

}
