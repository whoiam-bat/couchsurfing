/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Request } from '../../models/request';

export interface GetOutgoingRequest$Params {
  senderId: string;
  requestId: string;
}

export function getOutgoingRequest(http: HttpClient, rootUrl: string, params: GetOutgoingRequest$Params, context?: HttpContext): Observable<StrictHttpResponse<Request>> {
  const rb = new RequestBuilder(rootUrl, getOutgoingRequest.PATH, 'get');
  if (params) {
    rb.path('senderId', params.senderId, {});
    rb.path('requestId', params.requestId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Request>;
    })
  );
}

getOutgoingRequest.PATH = '/requests/{senderId}/outgoing/{requestId}';
