#import "ImmediateLoginPlugin.h"
#import <immediate_login/immediate_login-Swift.h>

@implementation ImmediateLoginPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftImmediateLoginPlugin registerWithRegistrar:registrar];
}
@end
