/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageReview } from '../../models/page-review';

export interface GetOutgoingReviews$Params {
  page: number;
  size: number;
  serviceType: 'ACCOMMODATION_PROVISION' | 'ACCOMMODATION_REQUEST';
}

export function getOutgoingReviews(http: HttpClient, rootUrl: string, params: GetOutgoingReviews$Params, context?: HttpContext): Observable<StrictHttpResponse<PageReview>> {
  const rb = new RequestBuilder(rootUrl, getOutgoingReviews.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
    rb.query('serviceType', params.serviceType, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageReview>;
    })
  );
}

getOutgoingReviews.PATH = '/reviews/outgoing';
