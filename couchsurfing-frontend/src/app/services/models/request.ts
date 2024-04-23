/* tslint:disable */
/* eslint-disable */
export interface Request {
  from?: string;
  id?: string;
  location?: string;
  message?: string;
  receiver?: string;
  requestStatus?: 'CREATED' | 'ACCEPTED' | 'DECLINED' | 'CANCELED' | 'COMPLETED';
  sender?: string;
  serviceType?: 'ACCOMMODATION_PROVISION' | 'ACCOMMODATION_REQUEST';
  timestamp?: string;
  to?: string;
  travelersAmount?: number;
}
