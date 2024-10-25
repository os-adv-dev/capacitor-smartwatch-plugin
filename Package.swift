// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorSmartwatchPlugin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorSmartwatchPlugin",
            targets: ["SmartwatchPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "SmartwatchPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/SmartwatchPlugin"),
        .testTarget(
            name: "SmartwatchPluginTests",
            dependencies: ["SmartwatchPlugin"],
            path: "ios/Tests/SmartwatchPluginTests")
    ]
)