import { CartDataType } from '@/types/ProductDetailDataType'
import { GetAPI, PostAPI } from './FetchAPI'
import { GetToken } from './GetToken'
const token = GetToken()

async function GetProductDataAPI(productId: string) {
    const response = await GetAPI(
        `/api/v1/product/detail/${productId}`,
        {
            cache: 'force-cache',
        },
        undefined,
    )
    return response
}
// async function GetProductReviewAPI(productId: string) {
//     const response = await GetAPI(
//         `/api/v1/review/list/${productId}`,
//         {
//             cache: 'force-cache',
//         },
//         undefined,
//     )
//     return response
// }
async function cartClickAPI(data: CartDataType) {
    const response = await PostAPI('/api/v1/cart', data, await token)
    return response
}

// async function recentProductAPI(productId: string) {
//     const response = await PostAPI(`/api/v1/recent/${productId}`, undefined, await token)

//     return response
// }

export { GetProductDataAPI, cartClickAPI }
