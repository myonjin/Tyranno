import { GetAPI, PostAPI } from './FetchAPI'
import { GetToken } from './GetToken'
const token = GetToken()

async function GetCategoryAPI() {
    const response = await GetAPI(`/api/v1/category`, undefined, undefined)

    return response
}

async function cartClickAPI(productId: string) {
    const response = await PostAPI(`/api/v1/cart/${productId}`, undefined, await token)
    return response
}

export { GetCategoryAPI, cartClickAPI }
