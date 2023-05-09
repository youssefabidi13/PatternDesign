import { Order } from "../services/cart-service.service";

export class Customer {
  public id?: number;
  
  public firstName: string;

  public lastName: string;

  public token: string;

  //authorities?: Authority[];

  public email: string;

  public bio: string;

  public phoneNumber: string;

  public password: string;

  public encryptedPassword: string;

  public profilePhoto: string;

  orders?: Order[];
  public authStatus : string;


  constructor(token?: string, id?: number, firstName?: string, lastName?: string, email?: string, bio?: string, phoneNumber?: string, password?: string, encryptedPassword?: string, profilePhoto?: string, authStatus?: string, orders?: Order[]) {
    
    this.token = token || '';
    this.id = id || 0;
    this.firstName = firstName || '';
    this.lastName = lastName || '';
    this.email = email  || '';

    this.bio = bio || '';
    this.phoneNumber = phoneNumber || '';
    this.password = password  || '';
    this.encryptedPassword = encryptedPassword || '';
    this.profilePhoto = profilePhoto || '';
    this.authStatus = authStatus || '';
    this.orders = orders || [];
  }
}
