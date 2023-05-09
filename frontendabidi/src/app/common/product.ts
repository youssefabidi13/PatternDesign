export class Product {
    id?: number
    sku?: string
    nbAchat?: number
    name?: string
    description?: string
    unitPrice?: number
    salesPrice?: number
    active?: boolean
    unitsInStock?: number
    dateCreated?: string
    lastUpdated?: string
    category?: string[]
    images?: Image[]
    sizes?: Size[]
}
export class Image {
    id?: number
    imageName?: string
  }
  
  export class Size {
    id?: number
    size?: string
  }
