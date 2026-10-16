/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package org.apache.commons.text.lookup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Provides access to lookups defined in this package.
 * <p>
 * Unless explicitly requested otherwise, a set of default lookups are included for convenience with these
 * variable interpolation methods. These defaults are listed in the table below. However, the exact lookups
 * included can be configured through the use of the {@value #DEFAULT_STRING_LOOKUPS_PROPERTY} system property.
 * If present, this system property will be parsed as a comma-separated list of lookup names, with the names
 * being those defined by the {@link DefaultStringLookup} enum. For example, setting this system property to
 * {@code "BASE64_ENCODER,ENVIRONMENT"} will only include the
 * {@link DefaultStringLookup#BASE64_ENCODER BASE64_ENCODER} and {@link DefaultStringLookup#ENVIRONMENT ENVIRONMENT}
 * lookups. Setting the property to the empty string will cause no defaults to be configured.
 * Note that not all lookups defined here and in {@link DefaultStringLookup} are included by default.
 * Specifically, lookups that can execute code (e.g., {@link DefaultStringLookup#SCRIPT SCRIPT}) and those
 * that can result in contact with remote servers (e.g., {@link DefaultStringLookup#URL URL}) are not included
 * by default. The current set of default lookups can be accessed directly with {@link #addDefaultStringLookups(Map)}.
 * </p>
 * <table>
 * <caption>Default String Lookups</caption>
 * <tr>
 * <th>Key</th>
 * <th>Implementation</th>
 * <th>Factory Method</th>
 * <th>Since</th>
 * </tr>
 * <tr>
 * <td>{@value #KEY_BASE64_DECODER}</td>
 * <td>{@link Base64DecoderStringLookup}</td>
 * <td>{@link #base64DecoderStringLookup()}</td>
 * <td>1.6</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_BASE64_ENCODER}</td>
 * <td>{@link Base64EncoderStringLookup}</td>
 * <td>{@link #base64EncoderStringLookup()}</td>
 * <td>1.6</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_CONST}</td>
 * <td>{@link ConstantStringLookup}</td>
 * <td>{@link #constantStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_DATE}</td>
 * <td>{@link DateStringLookup}</td>
 * <td>{@link #dateStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_ENV}</td>
 * <td>{@link EnvironmentVariableStringLookup}</td>
 * <td>{@link #environmentVariableStringLookup()}</td>
 * <td>1.3</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_FILE}</td>
 * <td>{@link FileStringLookup}</td>
 * <td>{@link #fileStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_JAVA}</td>
 * <td>{@link JavaPlatformStringLookup}</td>
 * <td>{@link #javaPlatformStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_LOCALHOST}</td>
 * <td>{@link LocalHostStringLookup}</td>
 * <td>{@link #localHostStringLookup()}</td>
 * <td>1.3</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_PROPERTIES}</td>
 * <td>{@link PropertiesStringLookup}</td>
 * <td>{@link #propertiesStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_RESOURCE_BUNDLE}</td>
 * <td>{@link ResourceBundleStringLookup}</td>
 * <td>{@link #resourceBundleStringLookup()}</td>
 * <td>1.6</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_SYS}</td>
 * <td>{@link SystemPropertyStringLookup}</td>
 * <td>{@link #systemPropertyStringLookup()}</td>
 * <td>1.3</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_URL_DECODER}</td>
 * <td>{@link UrlDecoderStringLookup}</td>
 * <td>{@link #urlDecoderStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_URL_ENCODER}</td>
 * <td>{@link UrlEncoderStringLookup}</td>
 * <td>{@link #urlEncoderStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_XML}</td>
 * <td>{@link XmlStringLookup}</td>
 * <td>{@link #xmlStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * </table>
 *
 * <table>
 * <caption>Additional String Lookups (not included by default)</caption>
 * <tr>
 * <th>Key</th>
 * <th>Implementation</th>
 * <th>Factory Method</th>
 * <th>Since</th>
 * </tr>
 * <tr>
 * <td>{@value #KEY_URL}</td>
 * <td>{@link UrlStringLookup}</td>
 * <td>{@link #urlStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>{@value #KEY_SCRIPT}</td>
 * <td>{@link ScriptStringLookup}</td>
 * <td>{@link #scriptStringLookup()}</td>
 * <td>1.5</td>
 * </tr>
 * </table>
 *
 * @since 1.3
 */
public final class StringLookupFactory {

    /**
     * Defines the singleton for this class.
     */
    public static final StringLookupFactory INSTANCE = new StringLookupFactory();

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_BASE64_DECODER = "base64Decoder";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_BASE64_ENCODER = "base64Encoder";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_CONST = "const";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_DATE = "date";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_ENV = "env";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_FILE = "file";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_JAVA = "java";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_LOCALHOST = "localhost";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_PROPERTIES = "properties";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_RESOURCE_BUNDLE = "resourceBundle";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_SCRIPT = "script";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_SYS = "sys";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_URL = "url";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_URL_DECODER = "urlDecoder";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_URL_ENCODER = "urlEncoder";

    /**
     * Default lookup key for interpolation.
     *
     * @since 1.6
     */
    public static final String KEY_XML = "xml";

    /**
     * Name of the system property used to determine the string lookups added by the
     * {@link #addDefaultStringLookups(Map)} method. Use of this property is only required
     * in cases where the set of default lookups must be modified. (See the class documentation
     * for details.)
     *
     * @since 1.10
     */
    public static final String DEFAULT_STRING_LOOKUPS_PROPERTY =
            "org.apache.commons.text.lookup.StringLookupFactory.defaultStringLookups";

    /**
     * Clears any static resources.
     *
     * @since 1.5
     */
    public static void clear() {
        ConstantStringLookup.clear();
    }

    /**
     * No need to build instances for now.
     */
    private StringLookupFactory() {
        // empty
    }

    /**
     * Adds the default string lookups for this class to {@code stringLookupMap}. The default string
     * lookups are a set of built-in lookups added for convenience during string interpolation. The
     * defaults may be configured using the {@value #DEFAULT_STRING_LOOKUPS_PROPERTY} system property.
     * See the class documentation for details and a list of lookups.
     *
     * @param stringLookupMap
     *            the map of string lookups to edit.
     * @since 1.5
     */
    public void addDefaultStringLookups(final Map<String, StringLookup> stringLookupMap) {
        if (stringLookupMap != null) {
            stringLookupMap.putAll(DefaultStringLookupsHolder.INSTANCE.getDefaultStringLookups());
        }
    }

    /**
     * Returns the Base64DecoderStringLookup singleton instance to format the current date with the format given in the
     * key in a format compatible with {@link java.text.SimpleDateFormat}.
     *
     * @return the Base64DecoderStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup base64DecoderStringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    /**
     * Returns the Base64EncoderStringLookup singleton instance to format the current date with the format given in the
     * key in a format compatible with {@link java.text.SimpleDateFormat}.
     *
     * @return the Base64EncoderStringLookup singleton instance.
     * @since 1.6
     */
    public StringLookup base64EncoderStringLookup() {
        return Base64EncoderStringLookup.INSTANCE;
    }

    /**
     * Returns the Base64DecoderStringLookup singleton instance to format the current date with the format given in the
     * key in a format compatible with {@link java.text.SimpleDateFormat}.
     *
     * @return the Base64DecoderStringLookup singleton instance.
     * @since 1.5
     * @deprecated Use {@link #base64DecoderStringLookup()}.
     */
    @Deprecated
    public StringLookup base64StringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    /**
     * Returns the ConstantStringLookup singleton instance to get the value of a fully-qualified static final value.
     *
     * @return the ConstantStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup constantStringLookup() {
        return ConstantStringLookup.INSTANCE;
    }

    /**
     * Returns the DateStringLookup singleton instance to format the current date with the format given in the key in a
     * format compatible with {@link java.text.SimpleDateFormat}.
     *
     * @return the DateStringLookup singleton instance.
     */
    public StringLookup dateStringLookup() {
        return DateStringLookup.INSTANCE;
    }

    /**
     * Returns the EnvironmentVariableStringLookup singleton instance where the lookup key is an environment variable
     * name.
     *
     * @return the EnvironmentVariableStringLookup singleton instance.
     */
    public StringLookup environmentVariableStringLookup() {
        return EnvironmentVariableStringLookup.INSTANCE;
    }

    /**
     * Returns the FileStringLookup singleton instance.
     * <p>
     * Looks up the value for the key in the format "CharsetName:Path".
     * </p>
     * <p>
     * For example: "UTF-8:com/domain/document.properties".
     * </p>
     *
     * @return the FileStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup fileStringLookup() {
        return FileStringLookup.INSTANCE;
    }

    /**
     * Returns a {@link InterpolatorStringLookup} containing the configured
     * {@link #addDefaultStringLookups(Map) default lookups}. See the class documentation for
     * details on how these defaults are configured.
     *
     * @return a new InterpolatorStringLookup.
     */
    public StringLookup interpolatorStringLookup() {
        return InterpolatorStringLookup.INSTANCE;
    }

    /**
     * Returns a new InterpolatorStringLookup. If {@code addDefaultLookups} is {@code true}, the configured
     * {@link #addDefaultStringLookups(Map) default lookups} are included in addition to the ones
     * provided in {@code stringLookupMap}. (See the class documentation for details on how default lookups
     * are configured.)
     *
     * @param stringLookupMap
     *            the map of string lookups.
     * @param defaultStringLookup
     *            the default string lookup; this lookup is used when a variable cannot be
     *            resolved using the lookups in {@code stringLookupMap} or the configured default lookups (if enabled)
     * @param addDefaultLookups
     *            whether to use default lookups as described above.
     * @return a new InterpolatorStringLookup.
     * @since 1.4
     */
    public StringLookup interpolatorStringLookup(final Map<String, StringLookup> stringLookupMap,
            final StringLookup defaultStringLookup, final boolean addDefaultLookups) {
        return new InterpolatorStringLookup(stringLookupMap, defaultStringLookup, addDefaultLookups);
    }

    /**
     * Returns a new InterpolatorStringLookup using the given key-value pairs and the configured
     * {@link #addDefaultStringLookups(Map) default lookups} to resolve variables. (See the class
     * documentation for details on how default lookups are configured.)
     *
     * @param <V>
     *            the value type the default string lookup's map.
     * @param map
     *            the default map for string lookups.
     * @return a new InterpolatorStringLookup.
     */
    public <V> StringLookup interpolatorStringLookup(final Map<String, V> map) {
        return new InterpolatorStringLookup(map);
    }

    /**
     * Returns a new InterpolatorStringLookup using the given lookup and the configured
     * {@link #addDefaultStringLookups(Map) default lookups} to resolve variables. (See the class
     * documentation for details on how default lookups are configured.)
     *
     * @param defaultStringLookup
     *            the default string lookup.
     * @return a new InterpolatorStringLookup.
     */
    public StringLookup interpolatorStringLookup(final StringLookup defaultStringLookup) {
        return new InterpolatorStringLookup(defaultStringLookup);
    }

    /**
     * Returns the JavaPlatformStringLookup singleton instance. Looks up keys related to Java: Java version, JRE
     * version, VM version, and so on.
     * <p>
     * The lookup keys with examples are:
     * </p>
     * <ul>
     * <li><b>version</b>: "Java version 1.8.0_181"</li>
     * <li><b>runtime</b>: "Java(TM) SE Runtime Environment (build 1.8.0_181-b13) from Oracle Corporation"</li>
     * <li><b>vm</b>: "Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)"</li>
     * <li><b>os</b>: "Windows 10 10.0, architecture: amd64-64"</li>
     * <li><b>hardware</b>: "processors: 4, architecture: amd64-64, instruction sets: amd64"</li>
     * <li><b>locale</b>: "default locale: en_US, platform encoding: iso-8859-1"</li>
     * </ul>
     *
     * @return the JavaPlatformStringLookup singleton instance.
     */
    public StringLookup javaPlatformStringLookup() {
        return JavaPlatformStringLookup.INSTANCE;
    }

    /**
     * Returns the LocalHostStringLookup singleton instance where the lookup key is one of:
     * <ul>
     * <li><b>name</b>: for the local host name, for example {@code EXAMPLE}.</li>
     * <li><b>canonical-name</b>: for the local canonical host name, for example {@code EXAMPLE.apache.org}.</li>
     * <li><b>address</b>: for the local host address, for example {@code 192.168.56.1}.</li>
     * </ul>
     *
     * @return the DateStringLookup singleton instance.
     */
    public StringLookup localHostStringLookup() {
        return LocalHostStringLookup.INSTANCE;
    }

    /**
     * Returns a new map-based lookup where the request for a lookup is answered with the value for that key.
     *
     * @param <V>
     *            the map value type.
     * @param map
     *            the map.
     * @return a new MapStringLookup.
     */
    public <V> StringLookup mapStringLookup(final Map<String, V> map) {
        return MapStringLookup.on(map);
    }

    /**
     * Returns the NullStringLookup singleton instance which always returns null.
     *
     * @return the NullStringLookup singleton instance.
     */
    public StringLookup nullStringLookup() {
        return NullStringLookup.INSTANCE;
    }

    /**
     * Returns the PropertiesStringLookup singleton instance.
     * <p>
     * Looks up the value for the key in the format "DocumentPath:Key".
     * </p>
     * <p>
     * For example: "com/domain/document.properties:Key".
     * </p>
     *
     * @return the PropertiesStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup propertiesStringLookup() {
        return PropertiesStringLookup.INSTANCE;
    }

    /**
     * Returns the ResourceBundleStringLookup singleton instance.
     * <p>
     * Looks up the value for a given key in the format "BundleName:BundleKey".
     * </p>
     * <p>
     * For example: "com.domain.messages:MyKey".
     * </p>
     *
     * @return the ResourceBundleStringLookup singleton instance.
     */
    public StringLookup resourceBundleStringLookup() {
        return ResourceBundleStringLookup.INSTANCE;
    }

    /**
     * Returns a ResourceBundleStringLookup instance for the given bundle name.
     * <p>
     * Looks up the value for a given key in the format "BundleKey".
     * </p>
     * <p>
     * For example: "MyKey".
     * </p>
     *
     * @param bundleName
     *            Only lookup in this bundle.
     * @return a ResourceBundleStringLookup instance for the given bundle name.
     * @since 1.5
     */
    public StringLookup resourceBundleStringLookup(final String bundleName) {
        return new ResourceBundleStringLookup(bundleName);
    }

    /**
     * Returns the ScriptStringLookup singleton instance. NOTE: This lookup is not included
     * as a {@link #addDefaultStringLookups(Map) default lookup} unless explicitly enabled. See
     * the class level documentation for details.
     * <p>
     * Looks up the value for the key in the format "ScriptEngineName:Script".
     * </p>
     * <p>
     * For example: "javascript:\"HelloWorld\"".
     * </p>
     *
     * @return the ScriptStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup scriptStringLookup() {
        return ScriptStringLookup.INSTANCE;
    }

    /**
     * Returns the SystemPropertyStringLookup singleton instance where the lookup key is a system property name.
     *
     * @return the SystemPropertyStringLookup singleton instance.
     */
    public StringLookup systemPropertyStringLookup() {
        return SystemPropertyStringLookup.INSTANCE;
    }

    /**
     * Returns the UrlDecoderStringLookup singleton instance.
     * <p>
     * Decodes URL Strings using the UTF-8 encoding.
     * </p>
     * <p>
     * For example: "Hello%20World%21" becomes "Hello World!".
     * </p>
     *
     * @return the UrlStringLookup singleton instance.
     * @since 1.6
     */
    public StringLookup urlDecoderStringLookup() {
        return UrlDecoderStringLookup.INSTANCE;
    }

    /**
     * Returns the UrlDecoderStringLookup singleton instance.
     * <p>
     * Decodes URL Strings using the UTF-8 encoding.
     * </p>
     * <p>
     * For example: "Hello World!" becomes "Hello+World%21".
     * </p>
     *
     * @return the UrlStringLookup singleton instance.
     * @since 1.6
     */
    public StringLookup urlEncoderStringLookup() {
        return UrlEncoderStringLookup.INSTANCE;
    }

    /**
     * Returns the UrlStringLookup singleton instance. This lookup is not included
     * as a {@link #addDefaultStringLookups(Map) default lookup} unless explicitly enabled. See
     * the class level documentation for details.
     * <p>
     * Looks up the value for the key in the format "CharsetName:URL".
     * </p>
     * <p>
     * For example, using the HTTP scheme: "UTF-8:http://www.google.com"
     * </p>
     * <p>
     * For example, using the file scheme:
     * "UTF-8:file:///C:/somehome/commons/commons-text/src/test/resources/document.properties"
     * </p>
     *
     * @return the UrlStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup urlStringLookup() {
        return UrlStringLookup.INSTANCE;
    }

    /**
     * Returns the XmlStringLookup singleton instance.
     * <p>
     * Looks up the value for the key in the format "DocumentPath:XPath".
     * </p>
     * <p>
     * For example: "com/domain/document.xml:/path/to/node".
     * </p>
     *
     * @return the XmlStringLookup singleton instance.
     * @since 1.5
     */
    public StringLookup xmlStringLookup() {
        return XmlStringLookup.INSTANCE;
    }

    /**
     * Internal class used to construct the default {@link StringLookup} map used by
     * {@link StringLookupFactory#addDefaultStringLookups(Map)}.
     */
    static final class DefaultStringLookupsHolder {

        /** Singleton instance, initialized with the system properties. */
        static final DefaultStringLookupsHolder INSTANCE = new DefaultStringLookupsHolder(System.getProperties());

        /** Default string lookup map. */
        private final Map<String, StringLookup> defaultStringLookups;

        /**
         * Construct a new instance initialized with the given properties.
         * @param props initialization properties
         */
        DefaultStringLookupsHolder(final Properties props) {
            final Map<String, StringLookup> lookups =
                    props.containsKey(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY)
                        ? parseStringLookups(props.getProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY))
                        : createDefaultStringLookups();

            defaultStringLookups = Collections.unmodifiableMap(lookups);
        }

        /**
         * Get the default string lookups map.
         * @return default string lookups map
         */
        Map<String, StringLookup> getDefaultStringLookups() {
            return defaultStringLookups;
        }

        /**
         * Create the lookup map used when the user has requested no customization.
         * @return default lookup map
         */
        private static Map<String, StringLookup> createDefaultStringLookups() {
            final Map<String, StringLookup> lookupMap = new HashMap<>();

            addLookup(DefaultStringLookup.BASE64_DECODER, lookupMap);
            addLookup(DefaultStringLookup.BASE64_ENCODER, lookupMap);
            addLookup(DefaultStringLookup.CONST, lookupMap);
            addLookup(DefaultStringLookup.DATE, lookupMap);
            addLookup(DefaultStringLookup.ENVIRONMENT, lookupMap);
            addLookup(DefaultStringLookup.FILE, lookupMap);
            addLookup(DefaultStringLookup.JAVA, lookupMap);
            addLookup(DefaultStringLookup.LOCAL_HOST, lookupMap);
            addLookup(DefaultStringLookup.PROPERTIES, lookupMap);
            addLookup(DefaultStringLookup.RESOURCE_BUNDLE, lookupMap);
            addLookup(DefaultStringLookup.SYSTEM_PROPERTIES, lookupMap);
            addLookup(DefaultStringLookup.URL_DECODER, lookupMap);
            addLookup(DefaultStringLookup.URL_ENCODER, lookupMap);
            addLookup(DefaultStringLookup.XML, lookupMap);

            return lookupMap;
        }

        /**
         * Construct a lookup map by parsing the given string. The string is expected to contain
         * comma or space-separated names of values from the {@link DefaultStringLookup} enum. If
         * the given string is null or empty, an empty map is returned.
         * @param str string to parse; may be null or empty
         * @return lookup map parsed from the given string
         */
        private static Map<String, StringLookup> parseStringLookups(final String str) {
            final Map<String, StringLookup> lookupMap = new HashMap<>();

            try {
                for (final String lookupName : str.split("[\\s,]+")) {
                    if (!lookupName.isEmpty()) {
                        addLookup(DefaultStringLookup.valueOf(lookupName.toUpperCase()), lookupMap);
                    }
                }
            } catch (IllegalArgumentException exc) {
                throw new IllegalArgumentException("Invalid default string lookups definition: " + str, exc);
            }

            return lookupMap;
        }

        /**
         * Add the key and string lookup from {@code lookup} to {@code map}, also adding any additional
         * key aliases if needed.
         * @param lookup lookup to add
         * @param map map to add to
         */
        private static void addLookup(final DefaultStringLookup lookup, final Map<String, StringLookup> map) {
            map.put(lookup.getKey(), lookup.getStringLookup());

            if (DefaultStringLookup.BASE64_DECODER.equals(lookup)) {
                // "base64" is deprecated in favor of KEY_BASE64_DECODER.
                map.put("base64", lookup.getStringLookup());
            }
        }
    }
}
