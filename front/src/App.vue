<template>
  <h1 class="header">和楽知屋 -WaGotcha-</h1>
    <nav>
      <ul>
        <li class="Tsutsumeki"><router-link to="/">ツツメキ</router-link></li>
        <li class="About"><router-link to="/about">和楽知屋とは</router-link></li>
      </ul>
      <router-view></router-view>
    </nav>
</template>