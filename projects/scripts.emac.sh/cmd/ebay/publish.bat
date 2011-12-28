del *.jar
del *.xml

set pwd=%cd%

C:\eclipse\eclipse -application org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher -metadataRepository file:/%pwd% -artifactRepository file:/%pwd% -source /%pwd% -compress -publishArtifacts