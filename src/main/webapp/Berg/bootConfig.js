this["sap-ui-config"] = {
  libs: "sap.ui.core, sap.m, sap.ui.layout",
  preload: "async",
  theme: "sap_belize",
  compatVersion: "edge",
  resourceRoots: {
    "demo": "./"
  },

  onInit: () => sap.ui.component({
    name: "demo",
    manifest: true,
  }).then(createdComponent => sap.ui.require([
    "sap/m/Shell",
    "sap/ui/core/ComponentContainer",
  ], (Shell, ComponentContainer) => new Shell({
    app: new ComponentContainer({
      component: createdComponent,
      height: "100%",
    }),
  }).placeAt("content"))),

  "xx-componentPreload": "off",
  "xx-waitForTheme": true,
  "xx-async": true,
  "xx-xml-processing": "sequential",
};