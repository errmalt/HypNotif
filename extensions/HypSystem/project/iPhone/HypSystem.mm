/**
 * ...
 * @author shoe[box]
 */
#include <HypSystem.h>
#import <UIKit/UIKit.h>
#include <netinet/in.h>
#import <SystemConfiguration/SCNetworkReachability.h>
#import <SystemConfiguration/SystemConfiguration.h>
namespace hyperfiction{

	bool isConnectedtoInternet( const char *sURL ){
		// Create zero addy
		 // Create zero addy
		 struct sockaddr_in zeroAddress;
		 bzero(&zeroAddress, sizeof(zeroAddress));
		 zeroAddress.sin_len = sizeof(zeroAddress);
		 zeroAddress.sin_family = AF_INET;

		 // Recover reachability flags
		 SCNetworkReachabilityRef defaultRouteReachability = SCNetworkReachabilityCreateWithAddress(NULL, (struct sockaddr *)&zeroAddress);
		 SCNetworkReachabilityFlags flags;

		 BOOL didRetrieveFlags = SCNetworkReachabilityGetFlags(defaultRouteReachability, &flags);
		 CFRelease(defaultRouteReachability);

		 if (!didRetrieveFlags){
		     printf("Error. Could not recover network reachability flags\n");
		     return 0;
		 }

		 BOOL isReachable = flags & kSCNetworkFlagsReachable;
		 BOOL needsConnection = flags & kSCNetworkFlagsConnectionRequired;
		 return (isReachable && !needsConnection) ? YES : NO;

	}

}