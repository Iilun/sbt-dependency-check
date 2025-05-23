package io.github.iilun.sbt.dependencycheck

import java.io.File

import org.owasp.dependencycheck.utils.Settings
import org.owasp.dependencycheck.utils.Settings.KEYS.*
import sbt.Logger

object DependencyCheckListSettingsTask {
  def logSettings(settings: Settings, failBuildOnCVSS: Float, formats: Seq[String], outputDirectory: String, scanSet: Seq[sbt.File],
                  skip: Boolean, skipRuntime: Boolean, skipTest: Boolean, skipProvided: Boolean, skipOptional: Boolean,
                  useSbtModuleIdAsGav: Boolean, log: Logger): Unit = {
    def logBooleanSetting(key: String, setting: String, log: Logger): Unit = {
      log.info(s"\t$setting: ${settings.getBoolean(key)}")
    }

    def logFloatSetting(key: String, setting: String, log: Logger): Unit = {
      log.info(s"\t$setting: ${settings.getFloat(key, 0)}")
    }

    def logStringSetting(key: String, setting: String, log: Logger): Unit = {
      log.info(s"\t$setting: ${if(key.contains("assword")) "******" else settings.getString(key)}")
    }

    def logFileSetting(key: String, setting: String, log: Logger): Unit = {
      val someFile: Option[File] = Option(settings.getFile(key))
      log.info(s"\t$setting: ${someFile.getOrElse(new File("")).getPath}")
    }

    def logUrlSetting(key: String, setting: String, log: Logger): Unit = {
      log.info(s"\t$setting: ${settings.getString(key)}")
    }

    logBooleanSetting(AUTO_UPDATE, "dependencyCheckAutoUpdate", log)
    logStringSetting(NVD_API_VALID_FOR_HOURS, "dependencyCheckCveValidForHours", log)
    log.info(s"\tdependencyCheckFailBuildOnCVSS: ${failBuildOnCVSS.toString}")
    logFloatSetting(JUNIT_FAIL_ON_CVSS, "dependencyCheckJUnitFailBuildOnCVSS", log)
    log.info(s"\tdependencyCheckFormats (combined with dependencyCheckFormat): ${formats.mkString(", ")}")
    log.info(s"\tdependencyCheckOutputDirectory: $outputDirectory")
    log.info(s"\tdependencyCheckScanSet: ${scanSet.map(f => f.getAbsolutePath).mkString(", ")}")
    log.info(s"\tdependencyCheckSkip: ${skip.toString}")
    log.info(s"\tdependencyCheckSkipTestScope: ${skipTest.toString}")
    log.info(s"\tdependencyCheckSkipRuntimeScope: ${skipRuntime.toString}")
    log.info(s"\tdependencyCheckSkipProvidedScope: ${skipProvided.toString}")
    log.info(s"\tdependencyCheckSkipOptionalScope: ${skipOptional.toString}")
    logFileSetting(SUPPRESSION_FILE, "dependencyCheckSuppressionFile/s", log)
    logFileSetting(HINTS_FILE, "dependencyCheckHintsFile", log)
    logStringSetting(ANALYSIS_TIMEOUT, "dependencyCheckAnalysisTimeout", log)
    logBooleanSetting(ANALYZER_EXPERIMENTAL_ENABLED, "dependencyCheckEnableExperimental", log)
    logBooleanSetting(ANALYZER_RETIRED_ENABLED, "dependencyCheckEnableRetired", log)

    // Analyzer Configuration
    logBooleanSetting(ANALYZER_ARCHIVE_ENABLED, "dependencyCheckArchiveAnalyzerEnabled", log)
    logStringSetting(ADDITIONAL_ZIP_EXTENSIONS, "dependencyCheckZipExtensions", log)
    logBooleanSetting(ANALYZER_JAR_ENABLED, "dependencyCheckJarAnalyzer", log)
    logBooleanSetting(ANALYZER_DART_ENABLED, "dependencyCheckDartAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_KNOWN_EXPLOITED_ENABLED,"dependencyCheckKnownExploitedEnabled", log)
    logUrlSetting(KEV_URL, "dependencyCheckKnownExploitedUrl", log)
    logStringSetting(KEV_CHECK_VALID_FOR_HOURS, "dependencyCheckKnownExploitedValidForHours", log)
    logBooleanSetting(ANALYZER_CENTRAL_ENABLED, "dependencyCheckCentralAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_CENTRAL_USE_CACHE, "dependencyCheckCentralAnalyzerUseCache", log)
    logBooleanSetting(ANALYZER_OSSINDEX_ENABLED, "dependencyCheckOSSIndexAnalyzerEnabled", log)
    logUrlSetting(ANALYZER_OSSINDEX_URL, "dependencyCheckOSSIndexAnalyzerUrl", log)
    logBooleanSetting(ANALYZER_OSSINDEX_USE_CACHE, "dependencyCheckOSSIndexAnalyzerUseCache", log)
    logBooleanSetting(ANALYZER_OSSINDEX_WARN_ONLY_ON_REMOTE_ERRORS, "dependencyCheckOSSIndexWarnOnlyOnRemoteErrors", log)
    logStringSetting(ANALYZER_OSSINDEX_USER, "dependencyCheckOSSIndexAnalyzerUsername", log)
    logStringSetting(ANALYZER_OSSINDEX_PASSWORD, "dependencyCheckOSSIndexAnalyzerPassword", log)
    logBooleanSetting(ANALYZER_NEXUS_ENABLED, "dependencyCheckNexusAnalyzerEnabled", log)
    logUrlSetting(ANALYZER_NEXUS_URL, "dependencyCheckNexusUrl", log)
    logBooleanSetting(ANALYZER_NEXUS_USES_PROXY, "dependencyCheckNexusUsesProxy", log)
    logStringSetting(ANALYZER_NEXUS_USER, "dependencyCheckNexusUser", log)
    logStringSetting(ANALYZER_NEXUS_PASSWORD, "dependencyCheckNexusPassword", log)
    logBooleanSetting(ANALYZER_PYTHON_DISTRIBUTION_ENABLED, "dependencyCheckPyDistributionAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_PYTHON_PACKAGE_ENABLED, "dependencyCheckPyPackageAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_RUBY_GEMSPEC_ENABLED, "dependencyCheckRubygemsAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_OPENSSL_ENABLED, "dependencyCheckOpensslAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_CMAKE_ENABLED, "dependencyCheckCmakeAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_AUTOCONF_ENABLED, "dependencyCheckAutoconfAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_MAVEN_INSTALL_ENABLED, "dependencyCheckMavenInstallAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_PIP_ENABLED, "dependencyCheckPipAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_PIPFILE_ENABLED, "dependencyCheckPipfileAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_POETRY_ENABLED, "dependencyCheckPoetryAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_COMPOSER_LOCK_ENABLED, "dependencyCheckComposerAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_CPANFILE_ENABLED, "dependencyCheckCpanFileAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_NODE_PACKAGE_ENABLED, "dependencyCheckNodeAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_NODE_PACKAGE_SKIPDEV, "dependencyCheckNodePackageSkipDevDependencies", log)
    logBooleanSetting(ANALYZER_NODE_AUDIT_ENABLED, "dependencyCheckNodeAuditAnalyzerEnabled", log)
    logUrlSetting(ANALYZER_NODE_AUDIT_URL, "dependencyCheckNodeAuditAnalyzerUrl", log)
    logBooleanSetting(ANALYZER_NODE_AUDIT_SKIPDEV, "dependencyCheckNodeAuditSkipDevDependencies" , log)
    logBooleanSetting(ANALYZER_NODE_AUDIT_USE_CACHE, "dependencyCheckNodeAuditAnalyzerUseCache", log)
    logBooleanSetting(ANALYZER_NPM_CPE_ENABLED, "dependencyCheckNPMCPEAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_YARN_AUDIT_ENABLED, "dependencyCheckYarnAuditAnalyzerEnabled", log)
    logFileSetting(ANALYZER_YARN_PATH, "dependencyCheckPathToYarn", log)
    logBooleanSetting(ANALYZER_PNPM_AUDIT_ENABLED, "dependencyCheckPNPMAuditAnalyzerEnabled", log)
    logFileSetting(ANALYZER_PNPM_PATH, "dependencyCheckPathToPNPM", log)
    logBooleanSetting(ANALYZER_NUSPEC_ENABLED, "dependencyCheckNuspecAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_NUGETCONF_ENABLED, "dependencyCheckNugetConfAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_COCOAPODS_ENABLED, "dependencyCheckCocoapodsEnabled", log)
    logBooleanSetting(ANALYZER_MIX_AUDIT_ENABLED, "dependencyCheckMixAuditAnalyzerEnabled", log)
    logFileSetting(ANALYZER_MIX_AUDIT_PATH, "dependencyCheckMixAuditPath", log)
    logBooleanSetting(ANALYZER_SWIFT_PACKAGE_MANAGER_ENABLED, "dependencyCheckSwiftEnabled", log)
    logBooleanSetting(ANALYZER_SWIFT_PACKAGE_RESOLVED_ENABLED, "dependencyCheckSwiftPackageResolvedAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_BUNDLE_AUDIT_ENABLED, "dependencyCheckBundleAuditEnabled", log)
    logFileSetting(ANALYZER_BUNDLE_AUDIT_PATH, "dependencyCheckPathToBundleAudit", log)
    logStringSetting(ANALYZER_BUNDLE_AUDIT_WORKING_DIRECTORY, "dependencyCheckBundleAuditWorkingDirectory", log)
    logBooleanSetting(ANALYZER_ASSEMBLY_ENABLED, "dependencyCheckAssemblyAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_MSBUILD_PROJECT_ENABLED, "dependencyCheckMSBuildAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_PE_ENABLED, "dependencyCheckPEAnalyzerEnabled", log)
    logFileSetting(ANALYZER_ASSEMBLY_DOTNET_PATH, "dependencyCheckPathToDotNETCore", log)
    logStringSetting(CVE_CPE_STARTS_WITH_FILTER, "dependencyCheckCpeStartsWith", log)
    logBooleanSetting(ANALYZER_RETIREJS_ENABLED, "dependencyCheckRetireJSAnalyzerEnabled", log)
    logBooleanSetting(ANALYZER_RETIREJS_FORCEUPDATE, "dependencyCheckRetireJSForceUpdate", log)
    logUrlSetting(ANALYZER_RETIREJS_REPO_JS_URL, "dependencyCheckRetireJSAnalyzerRepoJSUrl", log)
    logStringSetting(ANALYZER_RETIREJS_REPO_JS_USER, "dependencyCheckRetireJsAnalyzerRepoUser", log)
    logStringSetting(ANALYZER_RETIREJS_REPO_JS_PASSWORD, "dependencyCheckRetireJsAnalyzerRepoPassword", log)
    logStringSetting(ANALYZER_RETIREJS_REPO_VALID_FOR_HOURS, "dependencyCheckRetireJsAnalyzerRepoValidFor", log)
    logStringSetting(ANALYZER_RETIREJS_FILTERS, "dependencyCheckRetireJsAnalyzerFilters", log)
    logBooleanSetting(ANALYZER_RETIREJS_FILTER_NON_VULNERABLE, "dependencyCheckRetireJsAnalyzerFilterNonVulnerable", log)
    logBooleanSetting(ANALYZER_ARTIFACTORY_ENABLED, "dependencyCheckArtifactoryAnalyzerEnabled", log)
    logUrlSetting(ANALYZER_ARTIFACTORY_URL, "dependencyCheckArtifactoryAnalyzerUrl", log)
    logBooleanSetting(ANALYZER_ARTIFACTORY_USES_PROXY, "dependencyCheckArtifactoryAnalyzerUseProxy", log)
    logBooleanSetting(ANALYZER_ARTIFACTORY_PARALLEL_ANALYSIS, "dependencyCheckArtifactoryAnalyzerParallelAnalysis", log)
    logStringSetting(ANALYZER_ARTIFACTORY_API_USERNAME, "dependencyCheckArtifactoryAnalyzerUsername", log)
    logStringSetting(ANALYZER_ARTIFACTORY_API_TOKEN, "dependencyCheckArtifactoryAnalyzerApiToken", log)
    logStringSetting(ANALYZER_ARTIFACTORY_BEARER_TOKEN, "dependencyCheckArtifactoryAnalyzerBearerToken", log)
    logBooleanSetting(ANALYZER_GOLANG_DEP_ENABLED, "dependencyCheckGolangDepEnabled", log)
    logBooleanSetting(ANALYZER_GOLANG_MOD_ENABLED, "dependencyCheckGolangModEnabled", log)
    logFileSetting(ANALYZER_GOLANG_PATH, "dependencyCheckPathToGo", log)

    // Advanced Configuration
    logStringSetting(NVD_API_DATAFEED_USER, "dependencyCheckCveUser", log)
    logStringSetting(NVD_API_DATAFEED_PASSWORD, "dependencyCheckCvePassword", log)
    logStringSetting(NVD_API_KEY, "dependencyCheckCveApiKey", log)
    logStringSetting(NVD_API_DELAY, "dependencyCheckCveWaitTime", log)
    logStringSetting(NVD_API_DATAFEED_START_YEAR, "dependencyCheckCveStartYear", log)
    logStringSetting(CONNECTION_TIMEOUT, "dependencyCheckConnectionTimeout", log)
    logStringSetting(CONNECTION_READ_TIMEOUT, "dependencyCheckConnectionReadTimeout", log)
    logStringSetting(DB_FILE_NAME, "dependencyCheckDatabaseFileName", log)
    logStringSetting(DB_VERSION, "dependencyCheckDatabaseVersion", log)
    logFileSetting(DATA_DIRECTORY, "dependencyCheckDataDirectory", log)
    logStringSetting(DB_DRIVER_NAME, "dependencyCheckDatabaseDriverName", log)
    logFileSetting(DB_DRIVER_PATH, "dependencyCheckDatabaseDriverPath", log)
    logStringSetting(DB_CONNECTION_STRING, "dependencyCheckConnectionString", log)
    logStringSetting(DB_USER, "dependencyCheckDatabaseUser", log)
    logStringSetting(DB_PASSWORD, "dependencyCheckDatabasePassword", log)
    logBooleanSetting(HOSTED_SUPPRESSIONS_FORCEUPDATE, "dependencyCheckHostedSuppressionsForceUpdate", log)
    logBooleanSetting(HOSTED_SUPPRESSIONS_ENABLED, "dependencyCheckHostedSuppressionsEnabled", log)
    logUrlSetting(HOSTED_SUPPRESSIONS_URL, "dependencyCheckHostedSuppressionsUrl", log)
    logStringSetting(HOSTED_SUPPRESSIONS_VALID_FOR_HOURS, "dependencyCheckHostedSuppressionsValidForHours", log)

    log.info(s"\tdependencyCheckUseSbtModuleIdAsGav: ${useSbtModuleIdAsGav.toString}")
  }
}
