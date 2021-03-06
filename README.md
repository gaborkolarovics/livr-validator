# livr-validator

![build](https://github.com/gaborkolarovics/livr-validator/workflows/build/badge.svg?branch=master) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=gaborkolarovics_livr-validator&metric=alert_status)](https://sonarcloud.io/dashboard?id=gaborkolarovics_livr-validator) [![Maven Central](https://img.shields.io/maven-central/v/com.github.gaborkolarovics/livr-validator.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.gaborkolarovics%22%20AND%20a:%22livr-validator%22)

Lightweight validator supporting Language Independent Validation Rules Specification (LIVR)

## Description
See ['LIVR Specification'](http://livr-spec.org) for detailed documentation and list of supported rules.

Features:

 * Rules are declarative and language independent
 * Any number of rules for each field
 * Return together errors for all fields
 * Excludes all fields that do not have validation rules described
 * Has possibility to validatate complex hierarchical structures
 * Easy to describe and undersand rules
 * Returns understandable error codes(not error messages)
 * Easy to add own rules
 * Rules are be able to change results output ("trim", "nested\_object", for example)
 * Multipurpose (user input validation, configs validation, contracts programming etc)

## Usage

### Dependency

#### maven
```xml
<dependency>
  <groupId>com.github.gaborkolarovics</groupId>
  <artifactId>livr-validator</artifactId>
  <version>1.5.1</version>
</dependency>
```

#### Gradle
```js
implementation 'com.github.gaborkolarovics:livr-validator:1.5.1'
```

### Code

#### Schema source

* Simple string schema
```java
@LivrSchema(schema = "{\"name\": \"required\", \"email\": \"required\"}")
public class SamplePOJO{
    private String name;
    private String email;
    // Getter.. Setter..
}
```

* Classpath resource
```java
@LivrSchema(schema = "classpath:schemas/samplePOJO.json")
public class SamplePOJO{
    private String name;
    private String email;
    // Getter.. Setter..
}
```

* File resource
```java
@LivrSchema(schema = "file:/path/of/schemas/samplePOJO.json")
public class SamplePOJO{
    private String name;
    private String email;
    // Getter.. Setter..
}
```

#### Aliases

```java
@LivrSchema(schema = "{\"password\": \"strong_password\"}"
    aliases = { "{\"name\": \"strong_password\", \"rules\": {\"min_length\": 6}, \"error\": \"WEAK_PASSWORD\"}" }
    )
public class SamplePOJO{
    private String password;
    // Getter.. Setter..
}
```

Alias loader support `file:` and `classpath:` resource loading like schema.

#### Custom rule

* Pojo
```java
@LivrSchema(schema = "{\"name\": {\"my_length\": 50 }}"
	rules = { MyLength.class })
public class SamplePOJO{
    private String name;
    private String email;
    // Getter.. Setter..
}
```

* CustomRule
```java
public class MyLength implements Rule {

    @Override
    public Function<List<Object>, Function<FunctionKeeper, Object>> func() {

        return ruleDefinition -> {
            final Long maxLength = Long.valueOf(ruleDefinition.get(0) + "");

            return wrapper -> {
                if ((wrapper.getValue() == null) || (wrapper.getValue() + "").equals("")) {
                    return "";
                }

                final String value = wrapper.getValue() + "";
                if (value.length() > maxLength) {
                    return "MY_TOO_LONG";
                }
                wrapper.getFieldResultArr().add(value);
                return "";
            };
        };
    }

    @Override
    public String rule() {
        return "my_length";
    }

}
```

### Extra rules

This package contains `livr-extra-rules` module. See [README.md](livr-extra-rules/README.md).

```java
@LivrSchema(schema = "{\"id\": \"uuid\" }}"
	scanRulePackages = { "livr.validation.rules" })
```

## License

This repository is licensed under the [GNU Affero General Public License](https://www.gnu.org/licenses/agpl-3.0.en.html).

## Author

* Java (LIVR 2.0), maintainer vlbaluk (Vladislav Baluk)
* javax annotation, maintainer Gábor Kolárovics
