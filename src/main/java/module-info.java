module dev.mccue.microhttp.json {
    requires dev.mccue.reasonphrase;

    requires transitive dev.mccue.json;
    requires transitive dev.mccue.microhttp.handler;
    requires transitive org.microhttp;

    exports dev.mccue.microhttp.json;
}