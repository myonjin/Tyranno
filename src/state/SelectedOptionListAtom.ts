import { LastOptionListType } from '@/types/LastOptionType'
import { atom } from 'recoil'
import { recoilPersist } from 'recoil-persist'

const { persistAtom } = recoilPersist()

export const SelectedOptionItemListAtom = atom({
    key: 'SelectedOptionItemListAtom',
    default: [] as LastOptionListType[],
    effects_UNSTABLE: [persistAtom],
})
export const NoOptionItemListAtom = atom({
    key: 'NoOptionItemListAtom',
    default: [] as LastOptionListType[],
    effects_UNSTABLE: [persistAtom],
})
