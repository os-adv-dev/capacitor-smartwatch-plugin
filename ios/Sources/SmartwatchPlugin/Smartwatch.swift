import Foundation

@objc public class Smartwatch: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
