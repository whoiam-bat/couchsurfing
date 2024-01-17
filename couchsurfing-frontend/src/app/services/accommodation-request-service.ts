import {Injectable} from "@angular/core";
import {AccommodationRequest} from "../commons/accommodation/accommodation-request";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccommodationRequestService {

  private endpoint = 'http://localhost:8080/api/v1/requests';

  constructor(private httpClient: HttpClient) {
  }

  addNewTrip(accommodationRequest: AccommodationRequest): Observable<any> {
    return this.httpClient.post<AccommodationRequest>(this.endpoint, accommodationRequest);
  }

  addNewTripAndAskForHost(accommodationRequest: AccommodationRequest, hostId: string): Observable<any> {
    return this.httpClient.post<AccommodationRequest>(
      `${this.endpoint}/accommodation?hostId=${hostId}`,
      accommodationRequest
    );
  }

  getTrip(tripId: string): Observable<AccommodationRequest> {
    return this.httpClient.get<AccommodationRequest>(`${this.endpoint}/get/${tripId}`);
  }

  getIncomingRequestsPaginate(receiverId: string, page: number, size: number): Observable<AccommodationRequest[]> {
    return this.httpClient.get<AccommodationRequest[]>(
      `${this.endpoint}/${receiverId}/incoming?page=${page}&size=${size}`
    );
  }

  getOutgoingRequestsPaginate(senderId: string, page: number, size: number): Observable<AccommodationRequest[]> {
    return this.httpClient.get<AccommodationRequest[]>(
      `${this.endpoint}/${senderId}/outgoing?page=${page}&size=${size}`
    );
  }

  getOutgoingRequest(senderId: string, requestId: string): Observable<AccommodationRequest[]> {
    return this.httpClient.get<AccommodationRequest[]>(
      `${this.endpoint}/${senderId}/outgoing/${requestId}`
    );
  }

}
