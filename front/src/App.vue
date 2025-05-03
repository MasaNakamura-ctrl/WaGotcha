<template>
  <h1 class="header">WaGotcha!!</h1>
    <nav>
      <ul>
        <li class="Tsutsumeki"><router-link to="/">ツツメキ</router-link></li>
        <li class="About"><router-link to="/about">WaGotcha!!とは</router-link></li>
      </ul>
      <router-view></router-view>
    </nav>
</template>