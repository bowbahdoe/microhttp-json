# microhttp-json

Utility for producing JSON responses in microhttp

## Dependency Information

### Maven

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artiactId>microhttp-json</artiactId>
    <version>0.0.1</version>
</dependency>
```

### Gradle

```groovy
dependencies {
    implementation('dev.mccue:microhttp-json:0.0.1')
}
```

## Usage

```java
import dev.mccue.json.Json;
import dev.mccue.microhttp.handler.IntoResponse;
import dev.mccue.microhttp.handler.RouteHandler;
import dev.mccue.microhttp.json.JsonResponse;
import org.jspecify.annotations.Nullable;
import org.microhttp.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeHandler extends RouteHandler {
    public SomeHandler() {
        super("GET", Pattern.compile("/some/path"));
    }

    @Override
    protected IntoResponse handleRoute(Matcher matcher, Request request) {
        return new JsonResponse(Json.objectBuilder()
                .put("name", "victor")
                .put("rodger", true)
                .build()
        );
    }
}
```